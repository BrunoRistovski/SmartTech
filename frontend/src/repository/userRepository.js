import axiosInstance from '../axios/axios.js';

const userRepository = {
    register: async (data) => {
        return await axiosInstance.post("/users/register", data);
    },
    login: async (data) => {
        return await axiosInstance.post("/users/login", data);
    },
    edit: async (username, data) => {
        return await axiosInstance.put(`/users/edit/${username}`, data);
    },
};

export default userRepository;
