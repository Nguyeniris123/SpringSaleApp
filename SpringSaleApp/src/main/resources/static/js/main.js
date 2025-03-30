function deleteProduct(id) {
    if (confirm("Are you sure Delete?") === true) {
        fetch(`/SpringSaleApp/api/products/${id}`, {
            method: "delete"
        }).then(res => {
            if(res.status === 204) {
                alert("delete Success");
                location.reload();
            } else
                alert("error");
        });
    }
}
 