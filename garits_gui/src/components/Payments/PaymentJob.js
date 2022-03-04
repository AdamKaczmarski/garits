const PaymentJob = (props) => {
  const type = props.payment.payment_type.charAt(0).toUpperCase() +
  props.payment.payment_type.slice(1).toLowerCase();
  return (
    <tr>
      <td>{props.payment.id}</td>
      <td>{props.payment.customer_name}</td>
      <td>{type}</td>
      <td>{(Math.round(props.payment.amount * 100) / 100).toFixed(2)+" GBP"}</td>
      <td>{props.payment.job_id}</td>
      <td>{props.payment.create_date}</td>
      <td>{props.payment.payment_date}</td>
      <td>{props.payment.payment_due}</td>
    </tr>
  );
};

export default PaymentJob;
