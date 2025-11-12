import axiosInstance from "../axios/axios.js";

const orderRepository = {

    createOrder: async (data) => {
        return await axiosInstance.post("/orders/create", data);
    },

    submitOrder: async (id) => {
        return await axiosInstance.post(`/orders/submit/${id}`);
    },

    cancelOrder: async (id) => {
        return await axiosInstance.delete(`/orders/cancel/${id}`);
    }
};

export default orderRepository;
