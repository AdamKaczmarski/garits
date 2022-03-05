import Form from 'react-bootstrap/Form'

const SetPayDate = props => {
    return(
        <Form>
            <Form.Group controlId="setPayDate">
                <Form.Control type="date" defaultValue={new Date().toISOString().substring(0, 10)} />
            </Form.Group>
        </Form>
    );
}

export default SetPayDate;