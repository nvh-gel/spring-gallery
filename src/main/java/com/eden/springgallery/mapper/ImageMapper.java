package com.eden.springgallery.mapper;

import com.eden.springgallery.model.Image;
import com.eden.springgallery.viewmodel.ImageVM;
import org.mapstruct.Mapper;

/**
 * Mapstruct mapper for image
 */
@Mapper(componentModel = "spring")
public interface ImageMapper {

    /**
     * Map image model to view model
     *
     * @param image image model
     * @return image vm
     */
    ImageVM imageToImageVM(Image image);

    /**
     * Map image view model to model
     *
     * @param imageVM image view model
     * @return image
     */
    Image imageVMToImage(ImageVM imageVM);
}
