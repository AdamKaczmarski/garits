import axios from "axios";
import Button from "react-bootstrap/Button";
import { useContext } from "react";
import AuthContext from "../../store/auth-context";

const StockLedger = (props) => {
  const authCtx = useContext(AuthContext);
  const downloadStockLedger = async () => {
    try {
      const response = await axios({
        method: "GET",
        url: "http://localhost:8080/pdf/stock-ledger",
        headers: { Authorization: `Bearer ${authCtx.authData.token}` },
        responseType: 'blob'
      });
      if (response.status === 200) {
        const url = window.URL.createObjectURL(new Blob([response.data]));
        const link = document.createElement("a");
        link.href = url;
        link.setAttribute("download", "Stock_ledger_report.pdf");
        document.body.appendChild(link);
        link.click();
      }
    } catch (err) {
      console.log(err);
    }
  };

  return (
    <Button
      onClick={downloadStockLedger}
      className="mt-5 mb-auto mx-auto"
      style={{ width: "32rem" }}
    >
      Download
    </Button>
  );
};

export default StockLedger;
