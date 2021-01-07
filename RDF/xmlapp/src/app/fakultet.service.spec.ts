import { TestBed } from '@angular/core/testing';

import { FakultetService } from './fakultet.service';

describe('FakultetService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: FakultetService = TestBed.get(FakultetService);
    expect(service).toBeTruthy();
  });
});
