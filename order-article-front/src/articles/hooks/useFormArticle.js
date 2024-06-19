import { useFormik } from "formik";
import * as yup from "yup";
import { useEffect, useState } from "react";
import { articleService } from "../services/articleService";

const FORM_VALUES = {
  cod: "",
  name: "",
  price: "",
};

export const useFormArticle = () => {
  const validationSchema = yup.object({
    cod: yup
      .string("Enter code")
      .max(255, "max 255 values")
      .required("code is required"),
    name: yup
      .string("Enter  name")
      .max(255, "max 255 values")
      .required("name is required"),
    price: yup
      .number("Enter price")
      .required("price is required"),
  });

  const [loading, setLoading] = useState(false);

  const formArticle = useFormik({
    initialValues: FORM_VALUES,
    validationSchema,
    onSubmit: async (values) => {
      articleService
        .createArticle(values)
        .then((res) => {
          if (res.status === 201) {
            alert("Article saved successfully");
            formArticle.resetForm();
            setLoading(true);
          }
        })
        .catch((err) => {
          alert(err);
        });
    },
  });

  return {
    formArticle,
    loading,
    setLoading,
  };
};
