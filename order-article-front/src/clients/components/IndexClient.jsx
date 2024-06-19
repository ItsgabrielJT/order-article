import React, { useState } from "react";
import { useClients } from "../hooks/useClients";
import { useFormClient } from "../hooks/useFormClient";
import FormClient from "./FormClient";
import ListClients from "./ListClients";

function IndexClient() {
  const [clientID, setClientID] = useState(null);
  const { formClient, loading, setLoading } = useFormClient(clientID);
  const { clients } = useClients(loading, setLoading);

  const handleEdit = (item) => {
    setClientID(item);
  }

  return (
    <section>
      <FormClient formClient={formClient} />
      <ListClients clients={clients} onEdit={handleEdit}/>
    </section>
  );
}

export default IndexClient;
