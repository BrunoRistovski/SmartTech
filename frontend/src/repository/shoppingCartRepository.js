import axiosInstance from "../axios/axios.js";

const shoppingCartRepository = {
    findShoppingCartById: async (id) => {
        return await axiosInstance.get(`/shopping_cart/${id}`);
    },
    addItemToShoppingCart: async (data) => {
        return await axiosInstance.post("/shopping_cart/add_item", data);
    },
    deleteItemFromShoppingCart: async (id) => {
        return await axiosInstance.delete(`/shopping_cart/delete_item/${id}`);
    },
};

export default shoppingCartRepository;
