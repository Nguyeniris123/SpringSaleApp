import { useRef, useState } from "react";
import { Alert, Button, FloatingLabel, Form } from "react-bootstrap";
import Apis, { endpoints } from "../configs/Apis";
import { useNavigate } from "react-router-dom";
import MySpinner from "./layout/MySpinner";

const Register = () => {
    const info = [{
        label: "Tên",
        type: "text",
        field: "firstName"
    }, {
        label: "Họ và tên lót",
        type: "text",
        field: "lastName"
    }, {
        label: "Tên đăng nhập",
        type: "text", 
        field: "username"
    }, {
        label: "Mật khẩu",
        type: "password", 
        field: "password"
    }, {
        label: "Xác nhận mật khẩu",
        type: "password", 
        field: "confirm"
    }, {
        label: "Điện thoại",
        type: "tel", 
        field: "phone"
    }, {
        label: "Email",
        type: "email", 
        field: "email"
    }];
    const avatar = useRef();
    const [msg, setMsg] = useState("")

    const [user, setUser] = useState({});
    const [loading, setLoading] = useState(false);
    const nav = useNavigate();

    const setState = (value, field) => {
        setUser({...user, [field]: value});
    }

    const register = async (e) => {
        e.preventDefault();

        if (user.password !== user.confirm) {
            setMsg("Mật khẩu không khớp!");
        } else {
            try {
                setLoading(true);
                let form = new FormData();
                for (let f of info)
                    if (f.field !== 'confirm') {
                        form.append(f.field, user[f.field]);
                    }
    
                form.append('avatar', avatar.current.files[0]);
                console.info(form);
                let res = await Apis.post(endpoints['register'], form, {
                    headers: {
                        'Content-Type': 'multipart/form-data'
                    }
                });
                if (res.status === 201)
                    nav("/login");
            } catch {

            } finally {
                setLoading(false);
            }
        }
    }

    return (
        <>
            <h1 className="text-center text-success mt-1">ĐĂNG KÝ NGƯỜI DÙNG</h1>

            {msg && <Alert variant="danger">{msg}</Alert>}

            <Form onSubmit={register}>
                {info.map(f => <FloatingLabel key={f.field} controlId="floatingInput" label={f.label} className="mb-3">
                    <Form.Control type={f.type} placeholder={f.label} required value={user[f.field]} onChange={e => setState(e.target.value, f.field)} />
                </FloatingLabel>)}

                <FloatingLabel controlId="floatingInput" label="Ảnh sản phẩm" className="mb-3">
                    <Form.Control type="file" placeholder="Ảnh sản phẩm" ref={avatar} />
                </FloatingLabel>

                {loading ? <MySpinner />:<Button type="submit" variant="success" className="mt-1 mb-1">Đăng ký</Button>}
                
            </Form>
        </>
    )
}

export default Register;