export const CUSTOMERS = [
  {
    id: 1,
    name: "Company 1",
    address: "Street 1",
    postcode: "111E22",
    telephone_number: "1234567890",
    email: "company@test.com",
    fax: null,
    fixed_discount: 0,
    flex_discount: [
      { rangeFrom: 10000, discount: 5 },
      { rangeFrom: 20000, discount: 10 },
    ],
    service_discount: [
      {
        service_name: "MOT",
        discount: 15,
      },
      { service_name: "Oil change", discount: 10 },
    ],
  },
  {
    id: 2,
    name: "Company 2",
    address: "Street 2",
    postcode: "N17 E15",
    telephone_bumer: "1237657890",
    email: "company@test.com",
    fax: null,
    fixed_discount: 0,
    flex_discount: [
      { rangeFrom: 10000, discount: 5 },
      { rangeFrom: 20000, discount: 10 },
    ],
    service_discount: [
      {
        service_name: "MOT",
        discount: 16,
      },
      { service_name: "Oil change", discount: 11 },
    ],
  },
];
