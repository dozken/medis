export class Parameter {
  constructor(
    public id?: number,
    public type?: ParameterType,
    public value?: string,
    public childParams?: Parameter[]
  ) {
    this.id = id ? id : null;
    this.type = type ? type : null;
    this.value = value ? value : '';
    this.childParams = childParams ? childParams : [];
  }
}
export class ParameterSearch {
    constructor(public value?: string, public type?: ParameterType) {}
}
export enum ParameterType {
  DISEASES = 'DISEASES',
  ALLERGY_CATEGORY = 'ALLERGY_CATEGORY',
  ALLERGEN = 'ALLERGEN',
  REGION = 'REGION',
  CITY = 'CITY',
}
