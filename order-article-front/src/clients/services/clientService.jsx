import axios from "axios";
import { URL_BASE } from "../../constants";

export const clientService = {

  getClients: async function () {
    try {
      const res = await axios.get(URL_BASE + "clients");
      return res;
    } catch (error) {
      throw error
    }
  },

  createClient: async function (json) {

    try {
      const res = await axios.post(URL_BASE + "clients", json);
      return res;
    } catch (error) {
      throw error
    }
  },

  deleteClient: async function (id) {
    try {
      const res = await axios.delete(URL_BASE + "clients/" + id);
      return res;
    } catch (error) {
      throw error;
    }
  },

  getClientById: async function (id) {
    try {
      const res = await axios.get(URL_BASE + "clients/" + id);
      return res;
    } catch (error) {
      throw error;
    }
  },

  updateClient: async function (id, json) {
    try {
      const res = await axios.put(
        URL_BASE + "clients/" + id,
        json,        
      );
      return res;
    } catch (error) {
      throw error;
    }
  },

};