import {Patient} from "../patient/patient";

export class Doctor {
  uin: string;
  firstName: string;
  lastName: string;
  specialitiesText: string;
  specialities: string[];
  patients: Patient[];
}
