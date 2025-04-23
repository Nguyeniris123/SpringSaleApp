import { useContext, useState } from "react";
import { Alert, Button } from "react-bootstrap";
import Table from 'react-bootstrap/Table';
import cookie from 'react-cookies'
import { MyUserContext } from "../configs/MyContexts";
import { Link } from "react-router-dom";
import Apis, { endpoints } from "../configs/Apis";

const Cart = () => {
    const [cart, setCart] = useState(cookie.load('cart') || null);
    const user = useContext(MyUserContext);

    const pay = async () => {
        const res = await Apis.post(endpoints['pay'], Object.values(cart));
        if (res.status === 200) {
            setCart([]);
            cookie.remove('cart');
        }
    }

    return (
        <>
            <h1 className="text-center text-info mt-1">Giỏ hàng</h1>

            {cart === null? <>
                <Alert variant="warning">KHÔNG có sản phẩm nào trong giỏ!</Alert>
            </>:<>
                <Table striped bordered hover>
                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>Tên sản phẩm</th>
                            <th>Đơn gia</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        {Object.values(cart).map(c => <tr key={c.id}>
                            <td>{c.id}</td>
                            <td>{c.name}</td>
                            <td>{c.price}</td>
                            <td>{c.quantity}</td>
                        </tr>)}
                    </tbody>
                    
                </Table>
            </>}

            <div className="mt-1 mb-1">
                {user === null?<>
                    <p>Vui lòng <Link to="/login?next=/cart">đăng nhập</Link> để thanh toán!</p>
                </>:<>
                    <Button className="btn btn-info" onClick={pay}>Thanh toán</Button>
                </>}
                    
            </div>
        </>
    );
}

export default Cart;