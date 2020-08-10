export class Sample {
  constructor(public id?: string, public name?: string, public shortName?: string, public type?: SampleType) {
    this.id = id ? id : null;
    this.name = name ? name : '';
    this.shortName = shortName ? shortName : '';
    this.type = type ? type : null;
  }
}

export class SampleSearch {
  constructor(public name?: string, public shortName?: string, public type?: SampleType) {}
}

export enum SampleType {
  TYPE1 = 'TYPE1',
  TYPE2 = 'TYPE2',
  TYPE3 = 'TYPE3',
}
