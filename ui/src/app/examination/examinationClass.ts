import {Patient} from "../patient/patient";
import {Doctor} from "../doctor/doctor";
import {SickLeave} from "./sickLeave";

export class Examination {
  id: string;
  date: Date;
  diagnosis: string;
  prescription: string[];
  sickLeave: SickLeave;
  patient: Patient;
  doctor: Doctor;
}
