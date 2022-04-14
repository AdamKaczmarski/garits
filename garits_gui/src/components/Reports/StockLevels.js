import axios from "axios";
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import { useContext, useState } from "react";
import AuthContext from "../../store/auth-context";

const StockLevels = () => {
  const authCtx = useContext(AuthContext);
  const [dates, setDates] = useState({ dateFrom: "", dateTo: "" });
  //let dateFrom, dateTo;
  const downloadStockLevels = async () => {
    console.log(dates);
    try {
      const response = await axios({
        method: "GET",
        url: "http://localhost:8080/pdf/stock-level/" + dates.dateFrom + "/" + dates.dateTo,
        headers: {
          Authorization: `Bearer ${authCtx.authData.token}`,
          "Content-Type": "application/json",
        },
        //data: dates,
        responseType: "blob",
      });

      if (response.status === 200) {
        const url = window.URL.createObjectURL(new Blob([response.data]));
        const link = document.createElement("a");
        link.href = url;
        link.setAttribute("download", "Stock_level.pdf");
        document.body.appendChild(link);
        link.click();
      }
    } catch (err) {
      console.log(err);
    }
  };
  const date1Handler = (ev) => {
    setDates({ ...dates, dateFrom: ev.target.value });
    // dateFrom = ev.target.value;
  };
  const date2Handler = (ev) => {
    setDates({ ...dates, dateTo: ev.target.value });
    // dateTo = ev.target.value;
  };

  return (
    <>
      {" "}
      <Form className="mt-5 mb-auto mx-auto" style={{ width: "32rem" }}>
        <Form.Group controlId="setPayDate">
          <Form.Label>Date From</Form.Label>
          <Form.Control
            type="date"
            defaultValue={new Date().toISOString().substring(0, 10)}
            onChange={date1Handler}
          />
        </Form.Group>
        <Form.Group controlId="setPayDate">
          <Form.Label>Date To</Form.Label>
          <Form.Control
            type="date"
            defaultValue={new Date().toISOString().substring(0, 10)}
            onChange={date2Handler}
          />
        </Form.Group>
      </Form>
      <Button
        onClick={downloadStockLevels}
        className="mt-5 mb-auto mx-auto"
        style={{ width: "32rem" }}
      >
        Download
      </Button>
    </>
  );
};

export default StockLevels;
