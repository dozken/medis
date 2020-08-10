export class Diseases{
  constructor(public diseaseName?: string, public startDate?: Date, public endDate?: Date, public description?: string){
    this.diseaseName = diseaseName;
    this.startDate = startDate;
    this.endDate = endDate;
    this.description = description;
  }

}
