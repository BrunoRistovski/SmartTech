import axiosInstance from "../axios/axios.js";

const productRepository = {
    findAllProducts: async () => {
        return await axiosInstance.get("/products");
    },
    findProductById: async (id) => {
        return await axiosInstance.get(`/products/${id}`);
    },
    createProduct: async (data) => {
        return await axiosInstance.post("/products/admin/create", data);
    },
    updateProduct: async (id, data) => {
        return await axiosInstance.put(`/products/admin/update/${id}`, data);
    },
    deleteProductById: async (id) => {
        return await axiosInstance.delete(`/products/admin/delete/${id}`);
    },
};

export default productRepository;
