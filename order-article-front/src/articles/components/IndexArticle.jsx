import React from "react";
import FormArticle from "./FormArticle";
import ListArticles from "./ListArticles";
import { useFormArticle } from "../hooks/useFormArticle";
import { useArticle } from "../hooks/useArticle";


function IndexArticle() {
  const { formArticle, loading, setLoading } = useFormArticle();
  const { articles } = useArticle(loading, setLoading);

  return (
    <section>
      <FormArticle formArticle={formArticle} />
      <ListArticles articles={articles} />
    </section>
  );
}

export default IndexArticle;
