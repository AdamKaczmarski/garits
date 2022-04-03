import {useState, useEffect,useCallback} from 'react';
import axios from 'axios';
import Table from 'react-bootstrap/Table';
import { Spinner } from 'react-bootstrap';
const PaymentRetailDetails = props =>{
    const [isLoading,setIsLoading] = useState(true);
    const [paymentRetailDetails, setPaymentsRetailDetails] = useState([]);
    const obtainPaymentRetailDetails = async()=>{
        try{
            const response = await axios({
                method:"GET",
                url:`http://localhost:8080/payments/${props.idPayment}/retail-details`
            });
            console.log(response);
            setPaymentsRetailDetails(response.data);
        }catch(err){
            console.log(err)
        } finally {
            setIsLoading(false);
        }
    }
    useEffect(()=>{
        obtainPaymentRetailDetails();
    },[]);
    if (isLoading) {
        return <Spinner variant="primary" />;
    }
    let partsRetail;
    if (paymentRetailDetails && paymentRetailDetails.length>0){
        partsRetail = paymentRetailDetails.map((detail,index)=>{
            return(
                <tr key={index}>
                    <td>{detail.part.partName}</td>
                    <td>{detail.part.code}</td>
                    <td>{detail.part.price}</td>
                    <td>{detail.quantitySold}</td>
                    <td>{(Math.round(detail.part.price*detail.quantitySold * 100) / 100).toFixed(2) +
          " GBP"}</td>
                </tr>
            )
        })
    }
    return (
        <Table hover striped>
            <thead>
                <tr>
                    <th>Code</th>
                    <th>Part Name</th>
                    <th>Part Price</th>
                    <th>Quantity Sold</th>
                    <th>Total amount</th>
                </tr>
            </thead>
            <tbody>
                {partsRetail}
            </tbody>
        </Table>
    )
}

export default PaymentRetailDetails;