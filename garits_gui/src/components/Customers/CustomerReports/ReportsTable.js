import Table from "react-bootstrap/Table";
import Report from "./Report";
import { REPORTS } from "../../../dummy-data/reports";

const ReportTable = (props) => {
  const reports = REPORTS.filter(report=>report.customer_id===props.customer_id).map((report) => <Report report={report} />);
  return (
    <Table hover striped>
      <thead>
        <tr>
          <th>Date</th>
          <th>Name</th>
          <th>Type</th>
          <th></th>
        </tr>
      </thead>
      <tbody>{reports}</tbody>
    </Table>
  );
};

export default ReportTable;
