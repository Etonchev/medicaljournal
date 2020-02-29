import {Patient} from "../patient/patient";

export class Doctor {
  uin: string;
  firstName: string;
  lastName: string;
  specialities: Set<string>;
  patients: Array<Patient>;
}
