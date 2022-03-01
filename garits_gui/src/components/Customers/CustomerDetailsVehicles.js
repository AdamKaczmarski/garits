import VehiclesTable from '../Vehicles/VehiclesTable'
const CustomerDetailsVehicles = props => {
            return(
               <VehiclesTable customer_id={props.customer_id}/>
            );
}

export default CustomerDetailsVehicles;