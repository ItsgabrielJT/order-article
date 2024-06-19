import { useEffect, useState } from "react"
import { articleService } from "../services/articleService";


export const useArticle = (loading, setLoading) => {

    const [articles, setArticles] = useState([]);

    useEffect(() => {
        articleService.getArticles()
            .then((res) => {
                if ( res.status === 200 ) {
                    let data = [];
                    res.data.map((item, index) => {
                        data.push({
                            id: index + 1,
                            cod: item.cod,                           
                            name: item.name,
                            price: item.price,
                        });
                    });
                    setArticles(data);
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
        articles,
    }

}