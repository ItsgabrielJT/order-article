import { useEffect, useState } from "react"
import { clientService } from "../services/clientService";


export const useClients = (loading, setLoading) => {

    const [clients, setClients] = useState([]);

    useEffect(() => {
        clientService.getClients()
            .then((res) => {
                if ( res.status === 200 ) {
                    let data = [];
                    res.data.map((item, index) => {
                        data.push({
                            id: index + 1,
                            uuid: item.uuid, 
                            name: item.name,
                            lastname: item.lastname,                           
                        });
                    });
                    setClients(data);
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
        clients,
    }

}