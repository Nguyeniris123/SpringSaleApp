import { useContext, useEffect, useState } from "react";
import { Alert, Button, Card, Col, Row, Spinner } from "react-bootstrap";
import Apis, { endpoints } from "../configs/Apis";
import { useSearchParams } from "react-router-dom";
import MySpinner from "./layout/MySpinner";
import cookie from 'react-cookies'
import { MyCartDispatchContext } from "../configs/MyContexts";

const Home = () => {
    const [products, setProducts] = useState([]);
    const [page, setPage] = useState(1);
    const [loading, setLoading] = useState(false);
    const [q] = useSearchParams();
    const dispatch = useContext(MyCartDispatchContext);
    

    const loadProducts = async () => {
       if (page > 0) {
            try {
                setLoading(true);

                let url = `${endpoints['products']}?page=${page}`;

                let cateId = q.get("categoryId");
                
                if (cateId) {
                    url = `${url}&categoryId=${cateId}`
                }

                let kw = q.get('kw')
                if (kw) {
                    url = `${url}&kw=${kw}`
                }

                console.info(url)

                let res = await Apis.get(url);
                if (res.data.length === 0)
                    setPage(0);
                else
                    setProducts([...products, ...res.data]);
            } catch {

            } finally {
                setLoading(false)
            }
       }
    }

    useEffect(() => {
        loadProducts();
    }, [page, q]);

    useEffect(() => {
        setPage(1);
        setProducts([]);
    }, [q]);

    const loadMore = () => {
        if (!loading && page > 0)
            setPage(page + 1);
    }

    const addToCart = (product) => {
        let cart = cookie.load('cart') || {};
        if (product.id in cart) {
            cart[product.id]['quantity']++;
        } else {
            cart[product.id] = {
                "id": product.id,
                "name": product.name,
                "price": product.price,
                "quantity": 1
            }
        }

        dispatch({
            "type": "update"
        })

        cookie.save('cart', cart);
        console.info(cart);
    }

    return (
        <>
            {products.length === 0 && <Alert variant="info" className="mt-1">Không có sản phẩm nào!</Alert>}
            <Row>
                {products.map(p => <Col className="p-1" key={p.id} md={3} xs={6}>
                    <Card style={{ width: '18rem' }}>
                        <Card.Img variant="top" src={p.image} />
                        <Card.Body>
                            <Card.Title>{p.name}</Card.Title>
                            <Card.Text>
                            {p.price.toLocaleString()} VNĐ
                            </Card.Text>
                            <Button className="m-1" variant="primary">Xem chi tiết</Button>
                            <Button className="m-1" variant="danger" onClick={() => addToCart(p)}>Đặt hàng</Button>
                        </Card.Body>
                    </Card>
                </Col>)}
                
            </Row>
            {loading && <MySpinner />}

            {page > 0 && <div className="text-center m-1">
                <Button variant="success" onClick={loadMore}>Xem thêm...</Button>
            </div>}
           
        </>
    );
}

export default Home;