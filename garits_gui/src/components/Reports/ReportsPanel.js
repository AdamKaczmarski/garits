import { useState, useCallback, useEffect, useContext } from "react";
import AveragesForm from "./AveragesForm";
import Form from "react-bootstrap/Form";
import axios from "axios";
import Spinner from "react-bootstrap/Spinner";
import AuthContext from "../../store/auth-context";
import StockLedger from "./StockLedger";
import StockLevels from "./StockLevels";
import BookingStats from "./BookingStats";

const ReportsPanel = () => {
  const [isLoading, setIsLoading] = useState(true);
  const authCtx = useContext(AuthContext);
  const [chosenForm, setChosenForm] = useState(0);
  const downloadReport = async (id) => {

    
    try {
      const response = await axios({
        method: "GET",
        url: "http://localhost:8080/pdf/averages/" + id,
        headers: {
          Authorization: `Bearer ${authCtx.authData.token}`,
        },
       /*  data: {
          idUser: id,
        }, */
        responseType: "blob",
      });
      if (response.status === 200) {
        const url = window.URL.createObjectURL(new Blob([response.data]));
        const link = document.createElement("a");
        link.href = url;
        link.setAttribute("download", "Averages.pdf");
        document.body.appendChild(link);
        link.click();
      }
    } catch (err) {
      console.log(err);
    }
  };

  let form = null;
  switch (chosenForm) {
    case 0:
        form=<StockLedger />
      break;
    case 1: form=<StockLevels />
      break;
    case 2:
        form=<BookingStats />
      break;
    case 3:
        form=<AveragesForm downloadReport={downloadReport}/>
      break;

    default:
      form = null;
  }
  const formHandler = (ev) => {
    setChosenForm(+ev.target.value);
  };
  return (
    <>
      <Form className="mt-5 mb-auto mx-auto"  style={{ width: "32rem" }}>
        <Form.Group controlId="formSelector">
          <Form.Select onChange={formHandler}>
            <option value={0}>Stock Ledger</option>
            <option value={1}>Stock Levels</option>
            <option value={2}>Booking Statistics</option>
            <option value={3}>Average Jobs Report</option>
            {/*  <option value={4}>
                        Average Report
                    </option>
                    <option value={5}>
                        Average Report
                    </option> */}
          </Form.Select>
        </Form.Group>
      </Form>

      {form}
    </>
  );
};

export default ReportsPanel;
