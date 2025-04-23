import cookie from 'react-cookies'

const MyCartReducer = (current, action) => {
    if (action.type === 'update') {
        let cart = cookie.load('cart') || null;
        if (cart !== null) {
            let totalQuantity = 0;

            for (let c of Object.values(cart))
                totalQuantity += c.quantity;

            return totalQuantity;
        }
    }

    return current;
}

   


export default MyCartReducer;