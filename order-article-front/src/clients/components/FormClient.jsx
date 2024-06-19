import React from 'react'
import { Button } from "@mui/material";
import Box from "@mui/material/Box";
import TextField from "@mui/material/TextField";

function FormClient({ formClient }) {
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
        onSubmit={formClient.handleSubmit}
        noValidate
        autoComplete="off"
      >
        <div>
          <TextField
            margin="normal"
            fullWidth
            multiline
            name="name"
            label="Name"
            id="name"
            autoComplete="current-name"
            value={formClient.values.name}
            onChange={formClient.handleChange}
            onBlur={formClient.handleBlur}
            error={
              formClient.touched.name && Boolean(formClient.errors.name)
            }
            helperText={formClient.touched.name && formClient.errors.name}
          />
          <TextField
            margin="normal"
            fullWidth
            multiline
            name="lastname"
            label="Last Name"
            id="lastname"
            autoComplete="current-lastname"
            value={formClient.values.lastname}
            onChange={formClient.handleChange}
            onBlur={formClient.handleBlur}
            error={
              formClient.touched.lastname && Boolean(formClient.errors.lastname)
            }
            helperText={formClient.touched.lastname && formClient.errors.lastname}
          />
          <Button variant="contained" type="submit"  sx={{ mt:2.4 }}>Save</Button>
        </div>
      </Box>
  )
}

export default FormClient