import axios from "axios";
import { URL_BASE } from "../../constants";

export const orderService = {

  getOrders: async function () {
    try {
      const res = await axios.get(URL_BASE + "orders");
      return res;
    } catch (error) {
      throw error
    }
  },

  createOrder: async function (json) {

    try {
      const res = await axios.post(URL_BASE + "orders", json);
      return res;
    } catch (error) {
      throw error
    }
  },

};