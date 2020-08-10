import {Diseases} from './classes/diseases';
import {Allergy} from './classes/allergy';
import {Address} from './classes/address';

export class Patient{
  constructor(
    public id?: number,
    public name?: string,
    public surname?: string,
    public patronymic?: string,
    public pin?: number,
    public dateOfBirth?: Date,
    public gender?: GenderType,
    public diseases: Diseases[] = [],
    public allergy: Allergy[] = [],
    public address: Address[] = [],
  ) {
    this.id = id ? id : null;
    this.name = name ? name : '';
    this.surname = surname ? surname : '';
    this.patronymic = patronymic ? patronymic : '';
    this.pin = pin ? pin : null;
    this.dateOfBirth = dateOfBirth;
    this.gender = gender ? gender : null;
    this.diseases = diseases ? diseases : null;
    this.allergy = allergy ? allergy : null;
    this.address = address ? address : null;
  }
}

export class PatientSearch{
  constructor(
    public fullInfo?: string,
    public gender?: GenderType,
    public ageFrom?: number,
    public ageTo?: number,
  ) {}
}

export enum GenderType{
  MALE = 'MALE',
  FEMALE = 'FEMALE',
}
