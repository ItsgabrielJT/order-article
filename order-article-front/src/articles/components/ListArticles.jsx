import React from "react";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Paper from "@mui/material/Paper";

function ListArticles({ articles }) {

  if (!articles || articles.length === 0) {
    return <div>No articles available</div>;
  }

  return (
    <TableContainer component={Paper} sx={{ mt: 10 }}>
      <Table sx={{ minWidth: 650 }} aria-label="simple table">
        <TableHead>
          <TableRow>
            <TableCell align="right">ID</TableCell>
            <TableCell align="right">CODE</TableCell>
            <TableCell align="right">NAME</TableCell>
            <TableCell align="right">PRICE</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {articles.map((row) => (
            <TableRow key={row.id}>
              <TableCell align="right">{row.id}</TableCell>
              <TableCell align="right">{row.cod}</TableCell>
              <TableCell align="right">{row.name}</TableCell>
              <TableCell align="right">{row.price}</TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  );
}

export default ListArticles;
   