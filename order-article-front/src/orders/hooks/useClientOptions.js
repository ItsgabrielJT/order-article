import { useEffect, useState } from "react";
import { clientService } from "../../clients/services/clientService";

export const useClientOptions = () => {
  const [clients, setClients] = useState([]);

  useEffect(() => {
    clientService
      .getClients()
      .then((res) => {
        if (res.status === 200) {
          let data = [];
          res.data.map((item, index) => {
            data.push({
              label: `${item.name || "N/A"} ${item.lastname || "N/A"}`,
              value: item.uuid,
            });
          });
          setClients(data);
        } else {
          alert("error");
        }
      })
      .catch((err) => {
        alert(err);
      });
  }, []);

  return {
    clients,
  };
};
