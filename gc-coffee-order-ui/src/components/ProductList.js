import {Product} from "./Product";

export function ProductList({products=[], onAddClick}) {
    return (
        <>
            <h5 className="flex-grow-0"><b>상품 목록</b></h5>
            <ul className="list-group products">
                {products.map(v =>
                    <li key={v.productId} className="list-group-item d-flex mt-3">
                        <Product {...v} onAddClick={onAddClick} />
                    </li>
                )}
            </ul>
        </>
    )
}