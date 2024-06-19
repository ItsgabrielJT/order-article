import React from 'react'
import { Button } from "@mui/material";
import Box from "@mui/material/Box";
import TextField from "@mui/material/TextField";

function FormArticle({ formArticle }) {
  return (
    <Box
        component="form"
        sx={{
          "& .MuiTextField-root": { m: 1, width: "25ch" },
          display: "flex",
          flexDirection: "column",
          alignItems: "center",
          mt: 5,
        }}
        onSubmit={formArticle.handleSubmit}
        noValidate
        autoComplete="off"
      >
        <div>
          <TextField
            margin="normal"
            fullWidth
            multiline
            name="cod"
            label="Code"
            id="cod"
            autoComplete="current-cod"
            value={formArticle.values.cod}
            onChange={formArticle.handleChange}
            onBlur={formArticle.handleBlur}
            error={
              formArticle.touched.cod && Boolean(formArticle.errors.cod)
            }
            helperText={formArticle.touched.cod && formArticle.errors.cod}
          />
          <TextField
            margin="normal"
            fullWidth
            multiline
            name="name"
            label="Name"
            id="name"
            autoComplete="current-name"
            value={formArticle.values.name}
            onChange={formArticle.handleChange}
            onBlur={formArticle.handleBlur}
            error={
              formArticle.touched.name && Boolean(formArticle.errors.name)
            }
            helperText={formArticle.touched.name && formArticle.errors.name}
          />
          <TextField
            margin="normal"
            fullWidth
            multiline
            name="price"
            label="Price"
            id="price"
            autoComplete="current-price"
            value={formArticle.values.price}
            onChange={formArticle.handleChange}
            onBlur={formArticle.handleBlur}
            error={
              formArticle.touched.price && Boolean(formArticle.errors.price)
            }
            helperText={formArticle.touched.price && formArticle.errors.price}
          />
          <Button variant="contained" type="submit"  sx={{ mt:2.4 }}>Save</Button>
        </div>
      </Box>
  )
}

export default FormArticle