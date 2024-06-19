import { useFormik } from "formik";
import * as yup from "yup";
import { useEffect, useState } from "react";
import { orderService } from "../services/orderService";
import dayjs from "dayjs";

const FORM_VALUES = {
  cod: "",
  fecha: dayjs(new Date()),
  clientUuid: "",
  articleUuid: [],
};

export const useFormOrder = () => {
  const validationSchema = yup.object({
    cod: yup
      .string("Enter Code")
      .max(255, "max 255 values")
      .required("code is required"),
    fecha: yup
      .string("Enter Date")
      .required("Date is required"),
    clientUuid: yup
      .string("Select Client")
      .required("client is required"),
  });

  const [loading, setLoading] = useState(false);

  const formOrder = useFormik({
    initialValues: FORM_VALUES,
    validationSchema,
    onSubmit: async (values) => {
      console.log(values);
      orderService
        .createOrder(values)
        .then((res) => {
          if (res.status === 201) {
            alert("Order saved successfully");
            formOrder.resetForm();
            setLoading(true);
          }
        })
        .catch((err) => {
          alert(err);
        }); 
    },
  });

  return {
    formOrder,
    loading,
    setLoading,
  };
};
