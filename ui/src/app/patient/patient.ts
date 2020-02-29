export class Patient {
  egn: string;
  firstName: string;
  lastName: string;
  hasUninterruptedInsurance: boolean;

  toString() {
    return this.egn
      .concat(this.firstName)
      .concat(this.lastName);
  }
}
