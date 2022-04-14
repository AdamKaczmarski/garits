import axios from "axios";
import { useContext } from "react";
import AuthContext from "../../store/auth-context";
import Button from "react-bootstrap/Button";

const BookingStats =()=>{
    const authCtx = useContext(AuthContext);
    const downloadBookingStats = async () => {
      try {
        const response = await axios({
          method: "GET",
          url: "http://localhost:8080/pdf/booking-stats",
          headers: { Authorization: `Bearer ${authCtx.authData.token}` },
          responseType: 'blob'
        });
        if (response.status === 200) {
          const url = window.URL.createObjectURL(new Blob([response.data]));
          const link = document.createElement("a");
          link.href = url;
          link.setAttribute("download", "Booking_stats.pdf");
          document.body.appendChild(link);
          link.click();
        }
      } catch (err) {
        console.log(err);
      }
    };
  
    return (
        <Button
          onClick={downloadBookingStats}
          className="mt-5 mb-auto mx-auto"
          style={{ width: "32rem" }}
        >
          Download
        </Button>
      );

}

export default BookingStats;