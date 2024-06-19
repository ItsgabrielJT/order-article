import React from "react";
import {
  Button,
  FormControl,
  FormHelperText,
  InputLabel,
  MenuItem,
  Select,
} from "@mui/material";
import Box from "@mui/material/Box";
import TextField from "@mui/material/TextField";
import { useClientOptions } from "../hooks/useClientOptions";
import { useArticleOptions } from "../hooks/useArticleOptions";
import { LocalizationProvider } from "@mui/x-date-pickers/LocalizationProvider";
import { DatePicker } from "@mui/x-date-pickers/DatePicker";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";

const ITEM_HEIGHT = 48;
const ITEM_PADDING_TOP = 8;
const MenuProps = {
  PaperProps: {
    style: {
      maxHeight: ITEM_HEIGHT * 4.5 + ITEM_PADDING_TOP,
      width: 250,
    },
  },
};

function FormOrders({ formOrder }) {
  const { clients } = useClientOptions();
  const { articles } = useArticleOptions();

  const handleSelectClient = ({ target }) => {
    formOrder.setFieldValue("clientUuid", target.value);
  };

  const handleSelectArticle = ({ target }) => {
    formOrder.setFieldValue("articleUuid", target.value);
  };

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
      onSubmit={formOrder.handleSubmit}
      noValidate
      autoComplete="off"
    >
      <div style={{ alignItems: "center", padding: "20px" }}>
        <TextField
          margin="normal"
          multiline
          name="cod"
          label="Code"
          id="cod"
          autoComplete="current-cod"
          value={formOrder.values.cod}
          onChange={formOrder.handleChange}
          onBlur={formOrder.handleBlur}
          error={formOrder.touched.cod && Boolean(formOrder.errors.cod)}
          helperText={formOrder.touched.cod && formOrder.errors.cod}
          sx={{ mt: 10 }}
        />

        <LocalizationProvider dateAdapter={AdapterDayjs}>
          <DatePicker 
            
            value={formOrder.values.fecha} />
        </LocalizationProvider>

        <FormControl sx={{ m: 1, minWidth: 120 }}>
          <InputLabel id="demo-simple-select-error-label">Client</InputLabel>
          <Select
            labelId="demo-simple-select-error-label"
            id="demo-simple-select-error"
            value={formOrder.values.clientUuid}
            MenuProps={MenuProps}
            onChange={handleSelectClient}
            sx={{ width: 300 }}
          >
            {clients.map((item, index) => (
              <MenuItem key={index} value={item.value}>
                {item.label}
              </MenuItem>
            ))}
          </Select>
          {Boolean(formOrder.errors.clientUuid) && (
            <FormHelperText sx={{ text: "red" }}>
              {formOrder.errors.clientUuid}
            </FormHelperText>
          )}
        </FormControl>

        <FormControl sx={{ m: 1, minWidth: 120 }}>
          <InputLabel id="demo-simple-select-error-article-label">
            Article
          </InputLabel>
          <Select
            labelId="demo-simple-select-error-article-label"
            id="demo-simple-select-error"
            multiple
            value={formOrder.values.articleUuid}
            MenuProps={MenuProps}
            onChange={handleSelectArticle}
            sx={{ width: 300 }}
          >
            {articles.map((item, index) => (
              <MenuItem key={index} value={item.value}>
                {item.label}
              </MenuItem>
            ))}
          </Select>
        </FormControl>

        <Button variant="contained" type="submit" sx={{ ml: 2 }}>
          Save
        </Button>
      </div>
    </Box>
  );
}

export default FormOrders;
