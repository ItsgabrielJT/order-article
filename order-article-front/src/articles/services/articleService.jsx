import axios from "axios";
import { URL_BASE } from "../../constants";

export const articleService = {

  getArticles: async function () {
    try {
      const res = await axios.get(URL_BASE + "articles");
      return res;
    } catch (error) {
      throw error
    }
  },

  createArticle: async function (json) {

    try {
      const res = await axios.post(URL_BASE + "articles", json);
      return res;
    } catch (error) {
      throw error
    }
  },

};