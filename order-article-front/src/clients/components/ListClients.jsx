import React, { useState } from "react";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Paper from "@mui/material/Paper";
import { Button } from "@mui/material";
import { clientService } from "../services/clientService";

function ListClients({ clients, onEdit }) {



  if (!clients || clients.length === 0) {
    return <div>No clients available</div>;
  }

  const handleDelete = (item) => {
    clientService.deleteClient(item).then((res) => {
      if( res.status === 204 ) {
        alert("Client deleted successfully");
      }
    })
  };

  return (
    <TableContainer component={Paper} sx={{ mt: 10 }}>
      <Table sx={{ minWidth: 650 }} aria-label="simple table">
        <TableHead>
          <TableRow>
            <TableCell align="right">ID</TableCell>
            <TableCell align="right">NAME</TableCell>
            <TableCell align="right">LASTNAME</TableCell>
            <TableCell align="right">ACTIONS</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {clients.map((row) => (
            <TableRow key={row.id}>
              <TableCell align="right">{row.id}</TableCell>
              <TableCell align="right">{row.name}</TableCell>
              <TableCell align="right">{row.lastname}</TableCell>
              <TableCell align="right">
                <Button onClick={() => handleDelete(row.uuid)}>Eliminar</Button>
                <Button onClick={() => onEdit(row.uuid)}>Editar</Button>
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  );
}

export default ListClients;
