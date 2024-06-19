import { useFormik } from "formik";
import * as yup from "yup";
import { useEffect, useState } from "react";
import { clientService } from "../services/clientService";

const FORM_VALUES = {
  name: "",
  lastname: "",
};

export const useFormClient = (id = null) => {
  const validationSchema = yup.object({
    name: yup
      .string("Enter your name")
      .max(255, "max 255 values")
      .required("name is required"),
    lastname: yup
      .string("Enter your last name")
      .max(255, "max 255 values")
      .required("last name is required"),
  });

  const [loading, setLoading] = useState(false);

  useEffect(() => {
    if (id != null) {
      clientService
        .getClientById(id)
        .then((res) => {
          if (res.status === 200) {
            formClient.setValues({
              uuid: res.uuid,
              name: res.data.name,
              lastname: res.data.lastname,
            });
          }
        })
        .catch((err) => {
          alert(err);
        });
    }
    setLoading(false);
  }, [id]);

  const formClient = useFormik({
    initialValues: FORM_VALUES,
    validationSchema,
    onSubmit: async (values) => {
      if ( id != null ) {
        clientService
        .updateClient(id, values)
        .then((res) => {
          if (res.status === 200) {
            alert("Client update successfully");
            formClient.resetForm();
            setLoading(true);
          }
        })
        .catch((err) => {
          alert(err);
        });
      } else {
        clientService
        .createClient(values)
        .then((res) => {
          if (res.status === 201) {
            alert("Client saved successfully");
            formClient.resetForm();
            setLoading(true);
          }
        })
        .catch((err) => {
          alert(err);
        });
      }
    },
  });

  return {
    formClient,
    loading,
    setLoading,
  };
};
