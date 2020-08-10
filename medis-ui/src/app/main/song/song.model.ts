export class Song {
  constructor(public id?: string, public name?: string, public artist?: string, public genre?: GenreType, public releaseDate?: Date) {
    this.id = id ? id : null;
    this.name = name ? name : '';
    this.artist = artist ? artist : '';
    this.releaseDate = releaseDate ? releaseDate : null;
    this.genre = genre ? genre : null;
  }
}

export class SongSearch {
  constructor(public name?: string, public artist?: string, public genre?: GenreType) {}
}

export enum GenreType {
  GENRE1 = 'GENRE1',
  GENRE2 = 'GENRE2',
  GENRE3 = 'GENRE3',
}
