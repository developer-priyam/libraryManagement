import { TestBed } from '@angular/core/testing';

import { LibServiceService } from './lib-service.service';

describe('LibServiceService', () => {
  let service: LibServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LibServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
