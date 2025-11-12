import axiosInstance from "../axios/axios.js";

const paymentRepository = {
    checkout : async (data) =>{
        return await axiosInstance.post("/payment/checkout", data);
    }
};

export default paymentRepository;