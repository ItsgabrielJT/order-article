import { useEffect, useState } from "react"
import { orderService } from "../services/orderService";


export const useOrders = (loading, setLoading) => {

    const [orders, setOrders] = useState([]);

    useEffect(() => {
        orderService.getOrders()
            .then((res) => {
                if ( res.status === 200 ) {
                    let data = [];
                    res.data.map((item, index) => {
                        data.push({
                            id: index + 1,
                            cod: item.cod,
                            fecha: item.fecha,                           
                        });
                    });
                    setOrders(data);
                    setLoading(false);
                } else {
                    alert("error");
                }                    
            
            })
            .catch((err) => {
                alert(err);
            })
           
    }, [loading])

    return {
        orders,
    }

}