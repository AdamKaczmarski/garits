import {useState,useEffect,useCallback} from 'react';
import axios from 'axios';
import Spinner from 'react-bootstrap/Spinner'

const JobDetails = props => {
    const [isLoading, setIsLoading] = useState(true);
    const [job, setJob] = useState({});
    const obtainJobDetails = useCallback(async () => {
        try {
            const response = await axios({
                method:"GET",
                url:`http://localhost:8080/jobs/${props.idJob}`
            });
            console.log(response);
            setJob(response.data);
        }   catch (err) {
            console.log(err);
        } finally {
            setIsLoading(false);
        }
    },[props]);

    useEffect(() => {
        obtainJobDetails();
    },[obtainJobDetails]);
    if (isLoading) return (
        <Spinner animation="border" variant="primary" />
    );

    return(
        <></>
    );
}

export default JobDetails