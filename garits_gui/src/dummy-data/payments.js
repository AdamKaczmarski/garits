export const PAYMENTS_JOBS = [
  {
    id: 1,
    job_id: 1,
    customer_name: "Customer 1",
    payment_type: "card",
    amount: 102,
    create_date: "02-03-2022",
    payment_date: null,
    payment_due: "02-03-2022",
  },
  {
    id: 2,
    job_id: 2,
    customer_name: "Customer 1",
    payment_type: "cash",
    amount: 50,
    create_date: "02-03-2022",
    payment_date: "04-03-2022",
    payment_due: "02-03-2022",
  },
  {
    id: 3,
    job_id: 3,
    customer_name: "Customer 2",

    payment_type: "card",
    amount: 500,
    create_date: "02-03-2022",
    payment_date: "04-03-2022",
    payment_due: "02-03-2022",
  },
  {
    id: 4,
    job_id: 4,
    customer_name: "Customer 2",

    payment_type: "card",
    amount: 333,
    create_date: "01-02-2022",
    payment_date: "27-02-2022",
    payment_due: "01-03-2022",
  },
];

export const PAYMENTS_RETAIL = [
  {
    id_sale: 1,
    sale_date: "02-03-2022",
    payment_type: "cash",
    quantity_sold: 5,
    id_part: 1,
    part_name: "Airtex Water Block",
    price: 36.0,
  },
  {
    id_sale: 2,
    sale_date: "04-03-2022",
    part_id: 2,
    quantity_sold: 3,
    part_name: "Ford Grill",
    price: 109.99,
    payment_type: "card",
  },
];
