CREATE OR REPLACE FUNCTION sync_public_products()
RETURNS TRIGGER AS $$
BEGIN
    -- Si hay stock, insertar o actualizar en public_products
    IF NEW.stock > 0 THEN
        INSERT INTO public_products (nombre, precio, stock, product_id)
        VALUES (NEW.nombre, NEW.precio_venta, NEW.stock, NEW.id)
        ON CONFLICT (product_id)
        DO UPDATE SET
            nombre = EXCLUDED.nombre,
            precio = EXCLUDED.precio,
            stock  = EXCLUDED.stock;
    ELSE
        -- Si stock = 0, eliminar de public_products
        DELETE FROM public_products
        WHERE product_id = NEW.id;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;


//////////////////////

CREATE TRIGGER trg_sync_public_products
AFTER INSERT OR UPDATE ON products
FOR EACH ROW
EXECUTE FUNCTION sync_public_products();
