export class Allergy{
  constructor(public category?: string, public allergen?: string, public degree?: string){
      this.category = category ? category : '';
      this.allergen = allergen ? allergen : '';
      this.degree = degree ? degree : '';
  }

}
export enum Degree{
    HIGH = 'HIGH',
    MEDIUM = 'MEDIUM',
    LAW = 'LAW'
}
