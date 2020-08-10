export class Address {
  constructor(
    public region?: String,
    public city?: string,
    public address_type?: string,
    public address_name?: string,
    public phone_number?: string
  ) {
    this.region = region ? region : '';
    this.city = city ? city : '';
    this.address_type = address_type ? address_type : '';
    this.address_name = address_name ? address_name : '';
    this.phone_number = phone_number ? phone_number : '';
  }
}
