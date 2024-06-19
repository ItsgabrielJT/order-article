import { useEffect, useState } from "react";
import { articleService } from "../../articles/services/articleService";

export const useArticleOptions = () => {
  const [articles, setArticles] = useState([]);

  useEffect(() => {
    articleService
      .getArticles()
      .then((res) => {
        if (res.status === 200) {
          let data = [];
          res.data.map((item, index) => {
            data.push({
              label: `${item.cod || "N/A"} - ${item.name || "N/A"} - ${item.price || "N/A"}`,
              value: item.uuid,
            });
          });
          setArticles(data);
        } else {
          alert("error");
        }
      })
      .catch((err) => {
        alert(err);
      });
  }, []);

  return {
    articles,
  };
};
