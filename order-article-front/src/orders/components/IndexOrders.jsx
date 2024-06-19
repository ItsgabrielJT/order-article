import React from "react";
import { useOrders } from "../hooks/useOrders";
import { useFormOrder } from "../hooks/useFormOrder";
import FormOrders from "./FormOrders";
import ListOrders from "./ListOrders";

function IndexOrders() {
  const { formOrder, loading, setLoading } = useFormOrder();
  const { orders } = useOrders(loading, setLoading);

  return (
    <section>
      <FormOrders formOrder={formOrder} />
      <ListOrders orders={orders} />
    </section>
  );
}

export default IndexOrders;
